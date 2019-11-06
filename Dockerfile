FROM ubuntu

RUN apt-get update -y

# install git
RUN apt-get install -y git-core git

# install utilities
RUN apt-get install -y curl
RUN apt-get install -y wget
RUN apt-get install -y gnupg
RUN apt-get install -y unzip

# install java
RUN apt-get install -y openjdk-8-jdk

# install node
RUN apt-get install -y nodejs

# install yarn
#RUN apt-get install -y yarn
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add -
RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list
RUN apt-get update && apt-get install yarn

RUN yarn global add @angular/cli

RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
RUN apt-get update -y
RUN apt-get install -y google-chrome-stable

# install cf cli
RUN wget -q -O - https://packages.cloudfoundry.org/debian/cli.cloudfoundry.org.key | apt-key add -
RUN echo "deb https://packages.cloudfoundry.org/debian stable main" | tee /etc/apt/sources.list.d/cloudfoundry-cli.list
RUN apt-get update -y
RUN apt-get install -y cf-cli

# install Ruby
RUN apt-get update -y
RUN apt-get install -y autoconf bison build-essential libssl-dev libyaml-dev libreadline6-dev zlib1g-dev libncurses5-dev libffi-dev libgdbm-dev
RUN apt-get clean -y

RUN cd ~
RUN git clone https://github.com/rbenv/rbenv.git ~/.rbenv
RUN git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build
ENV HOME "/root"
ENV PATH "$HOME/.rbenv/bin:$HOME/.rbenv/plugins/ruby-build/bin:$HOME/.rbenv/shims:$PATH"
RUN echo 'eval "$(rbenv init -)"' >> ~/.bash_profile
RUN /bin/bash -c "source ~/.bash_profile"
RUN rbenv install -v 2.5.1
RUN rbenv global 2.5.1
RUN echo "gem: --no-document" > ~/.gemrc
RUN gem install bundler

# Install and start mysql server
RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y libmysqlclient-dev mysql-server
RUN apt-get update && apt-get install -y mysql-server
RUN find /var/lib/mysql -type f -exec touch {} \; && service mysql start && echo "USE mysql; UPDATE user SET plugin='mysql_native_password' WHERE User='root';" | mysql -u root && echo "create database stargate_test" | mysql -u root

#Install and start redis server
RUN apt-get install -y redis-server
RUN echo "daemonize yes" >> /etc/redis/redis.conf
RUN echo "requirepass password" >> /etc/redis/redis.conf
RUN sed 's/^bind 127.*/bind 127.0.0.1/' -i /etc/redis/redis.conf

# Install and start mailcatcher
RUN apt-get install libsqlite3-dev
RUN gem install mailcatcher

# Install Gradle and Ruby dependencies
COPY . /tmp/stargate
WORKDIR /tmp/stargate
ARG nexus_username
ARG nexus_password
ENV NEXUS_USERNAME=$nexus_username
ENV NEXUS_PASSWORD=$nexus_password
RUN ./gradlew downloadDependencies

COPY scripts/services-init.sh /tmp/
RUN chmod +x /tmp/services-init.sh
ENTRYPOINT "/tmp/services-init.sh" && /bin/bash
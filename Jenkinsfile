pipeline {
    agent {
        docker {
            image 'stargate:0.11.1'
            args '-u root:root'
            label 'docker'
            registryUrl 'https://docker-di2e.di2e.net'
            registryCredentialsId '687110ca-29bc-48c6-b35a-50b6040f1260'
        }
    }
    options { timestamps() }

    parameters {
        string(name: 'CF_HOME', defaultValue: '/tmp', description: 'Location for cf config')
    }

    environment {
        JIRA_PROJECT_NAME = 'jenkins'
        JIRA_PROJECT_KEY = 'JEN'
    }

    stages {
        stage('Clean') {
            steps {
                runCommand('./gradlew clean', 'Clean', true)
            }
        }
        stage('UI Tests') {
            steps {
                runCommand('./gradlew components:ui:test', 'UI Tests', true)
            }
        }
        stage('API Tests') {
            steps {
                runCommand('./gradlew components:api:test', 'API Tests', true)
            }
        }
        stage('PRISM Adapter Tests') {
            steps {
                runCommand('./gradlew components:prism-adapter:test', 'PRISM Adapter Tests', true)
            }
        }
        stage('Acceptance Tests') {
            steps {
                runCommand('./gradlew acceptance:ciTest', 'Acceptance Tests', true)
            }
        }
        stage('Build') {
            parallel {
                stage('Build Stargate') {
                    steps {
                        runCommand('./gradlew components:ui:build', 'Build UI for Stargate', true)
                        runCommand('./gradlew components:api:assemble', 'Build Stargate', true)
                    }
                }
                stage('Build PRISM Adapter') {
                    steps {
                        runCommand('./gradlew components:prism-adapter:assemble', 'Build PRISM Adapter', true)
                    }
                }
            }
        }
        stage('Deploy') {
            parallel {
                stage('Deploy Stargate to GovCloud') {
                    steps {
                        runCommand('./scripts/blue-green-deploy-stargate.sh govcloud', 'Deploy Stargate to GovCloud', true)
                    }
                }
                stage('Deploy PRISM Adapter to GovCloud') {
                    steps {
                        runCommand('./scripts/blue-green-deploy-prism-adapter.sh govcloud', 'Deploy PRISM Adapter to GovCloud', true)
                    }
                }
                stage('Deploy PRISM Mock to GovCloud') {
                    steps {
                        runCommand('./scripts/deploy-prism-mock-govcloud.sh', 'Deploy PRISM Mock to GovCloud', true)
                    }
                }
            }
        }
        stage('SonarQube') {
            steps {
                sh './gradlew sonarqube -Dsonar.host.url=https://sonarqube.di2e.net -Dsonar.login=a41e26519720a925e6344920063e613a34423b27 || true'
            }
        }
    }
}

void runCommand(command, stageName, failOnError) {
    RETURN_VAL = sh([script: command, returnStatus: true])
    if (RETURN_VAL == 1) {
        env.ForEmailPlugin = env.WORKSPACE
        emailext attachmentsPattern: 'TestResults\\*.trx',
        body: 'Build failure occurred in ' + stageName + '<br>' + 'See ' + env.BUILD_URL,
        subject: "BUILD FAILED: " + env.JOB_NAME,
        to: 'ryan.andrews@polarisalpha.com'
    }

    if (RETURN_VAL == 1 && failOnError) {
        error('Build failed in ' + stageName)
    }
}

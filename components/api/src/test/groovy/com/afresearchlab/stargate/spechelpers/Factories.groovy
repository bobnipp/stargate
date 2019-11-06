package com.afresearchlab.stargate.spechelpers

import com.afresearchlab.stargate.users.UserDTO

class Factories {
    static UserDTO makeUser(UserDTO overrides) {
        return new UserDTO(
            username: overrides.username ? overrides.username : 'user1',
            firstName: overrides.firstName ? overrides.firstName : 'Larry',
            lastName: overrides.lastName ? overrides.lastName : 'Jones',
            email: overrides.email ? overrides.email : 'larry.jones@important.mil.gov'
        )
    }
}

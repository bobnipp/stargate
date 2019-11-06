package com.afresearchlab.stargate.users

import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class UserRepositorySpec extends IntegrationSpec {

    @Autowired
    UserRepository subject

    def 'it can retrieve a user by id'() {
        given:
        def savedUser = subject.save(new UserDTO(username: 'foo', firstName: 'Arnold', lastName: 'Stallone', email: 'a_s@imm.com', notes: 'Test notes'))

        when:
        def user = subject.getUserById(savedUser.id)

        then:
        user.id != null
        user.firstName == 'Arnold'
        user.lastName == 'Stallone'
        user.email == 'a_s@imm.com'
        user.notes == 'Test notes'
    }

    def 'it can retrieve a user by username'() {
        given:
        subject.save(new UserDTO(username: 'foo', firstName: 'Arnold', lastName: 'Stallone', email: 'a_s@imm.com', notes: 'Test notes'))

        when:
        def user = subject.getUserByUsername('foo')

        then:
        user.id != null
        user.firstName == 'Arnold'
        user.lastName == 'Stallone'
        user.email == 'a_s@imm.com'
        user.notes == 'Test notes'
    }

    def 'it can retrieve a list of users'() {
        given:
        subject.save(new UserDTO(username: 'foo', firstName: 'Arnold', lastName: 'Stallone', email: 'a_s@imm.com', notes: 'Test notes'))
        subject.save(new UserDTO(username: 'foo', firstName: 'Sylvester', lastName: 'Schwarzenegger', email: 's_a@imm.com', notes: 'Test notes 2'))

        when:
        def users = subject.getUsers()

        then:
        users.size() == 2
        users.firstName.containsAll(["Arnold", "Sylvester"])
        users.lastName.containsAll(["Stallone", "Schwarzenegger"])
        users.email.containsAll(["a_s@imm.com", "s_a@imm.com"])
        users.notes.containsAll(["Test notes", "Test notes 2"])
    }

    def 'it can save a new user'() {
        when:
        def user = subject.save(new UserDTO(username: 'foo', firstName: 'Bruce', lastName: 'Willis', email: 'b_w@imm.com', notes: 'Test notes'))

        then:
        user.id != null
        user.firstName == 'Bruce'
        user.lastName == 'Willis'
        user.email == 'b_w@imm.com'
        user.notes == 'Test notes'
    }
}

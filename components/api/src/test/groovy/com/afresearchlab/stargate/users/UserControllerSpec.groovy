package com.afresearchlab.stargate.users

import com.afresearchlab.stargate.spechelpers.ValidationSpec
import com.google.gson.reflect.TypeToken
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest
import static com.afresearchlab.stargate.spechelpers.Factories.makeUser
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given

class UserControllerSpec extends ValidationSpec {
    @Autowired
    UserRepository userRepo

    @Autowired
    UserController controller

    def USER_LIST_TYPE = new TypeToken<List<UserDTO>>() {}.getType()

    @Unroll
    def 'it returns the user from the database by id #id'() {
        given:
        def user = userRepo.save(userDto)

        expect:
        def result = baseRequest(controller)
            .when()
            .get("/api/v1/users/${user.id}")
        assertSuccess(result)

        UserDTO foundUser = deserializeWithValidation(result, UserDTO.class, 'user/user')
        foundUser.firstName == user.firstName
        foundUser.lastName == user.lastName

        where:
        userDto                                                       | _
        makeUser(new UserDTO(firstName: "Bill", lastName: "Brasky"))  | _
        makeUser(new UserDTO(firstName: "Who", lastName: "Whatever")) | _
    }

    @Unroll
    def 'it returns current user #username'() {
        expect:
        def result = given()
            .standaloneSetup(controller)
            .auth()
            .principal({ _ -> username }())
            .when()
            .get("/api/v1/users/current")
        assertSuccess(result)

        UserDTO user = deserializeWithValidation(result, UserDTO.class, 'user/user')
        user.username == username

        where:
        username | _
        'foo'    | _
        'bar'    | _
    }

    def 'getAll returns the list of users from the database'() {
        given:
        userRepo.save(makeUser(new UserDTO(firstName: 'Arnold', lastName: "Schwarzenegger", email: 'as@example.com')))
        userRepo.save(makeUser(new UserDTO(firstName: 'Jean Claude', lastName: "Van Damme", email: 'jc@example.com')))

        expect:
        def result = baseRequest(controller)
            .when()
            .get("/api/v1/users")
        assertSuccess(result)

        List<UserDTO> users = deserializeWithValidation(result, USER_LIST_TYPE, 'user/user-list')
        users[0].firstName == 'Arnold'
        users[0].lastName == 'Schwarzenegger'
        users[0].email == 'as@example.com'
        users[1].firstName == 'Jean Claude'
        users[1].lastName == 'Van Damme'
        users[1].email == 'jc@example.com'
    }

    def 'it can create a user'() {
        expect:
        def result = baseRequest(controller)
            .body(getJson('user/user'))
            .when()
            .post('/api/v1/users')
        assertSuccess(result)

        UserDTO user = deserializeWithValidation(result, UserDTO.class, 'user/user')
        user.firstName == 'Bruce'
        user.lastName == 'Willis'
        user.email == 'bw@example.com'
        user.id != null
    }
}

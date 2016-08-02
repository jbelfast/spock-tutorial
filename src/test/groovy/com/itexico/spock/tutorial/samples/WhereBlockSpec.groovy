package com.itexico.spock.tutorial.samples

import spock.lang.Specification


/**
 * Created by Juan Malacrida on 19-Jul-16.
 */
class WhereBlockSpec extends Specification {
    def 'should select max of two numbers'() {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c // these are vars will be available above in test,
        5 | 1 | 5 // initialized to these values!
        9 | 9 | 9
    }

    def 'should sort list of numbers'() {
        given:
        def list = [a, b]

        when:
        def calculated = list.sort()

        then:
        calculated == expectedResult

        where:
        a << [5, 9]
        b << [1, 9]
        expectedResult << [[1, 5], [9, 9]]
    }

}
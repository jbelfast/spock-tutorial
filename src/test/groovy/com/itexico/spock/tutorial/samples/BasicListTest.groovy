package com.itexico.spock.tutorial.samples

import spock.lang.Specification

/**
 * Created by Juan Malacrida on 19-Jul-16.
 */
class BasicListTest extends Specification {
    def "should not be empty after adding element"() {
        given: 'An empty list'
        def list = [] // [] is Groovy literal for List and is inferred

        when: 'We add a value'
        list.add(42)

        then: 'Its size is 1'
        // Asserts are implicit and not need to be stated.
        // Change "==" to "!=" and see what's happening!
        list.size() == 1

        and: 'Its value is retrieved properly'
        list == [42]
    }

    def "should be empty after removing element"() {
        given: 'A non-empty list'
        def list = [42] // it's a List

        when: 'We remove its only value'
        list.remove(0)

        then: 'The list is empty'
        // Spock can assert than an Exception was _NOT_ thrown:
        notThrown(IllegalArgumentException)
        // can call helper method here:
        doCommonAssertions(list)
    }

    // Assertion helpers have to have void return type and explicit asserts.
    def void doCommonAssertions(List<Integer> list) { // type is optional
        assert list != null
        assert list.isEmpty()
    }
}

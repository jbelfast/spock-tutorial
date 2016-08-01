package com.itexico.spock.tutorial.samples

import spock.lang.Specification

/**
 * Created by Juan Malacrida on 20-Jul-16.
 */
class Publisher {
    def subscribers = []

    def send(event) {
        subscribers.each {
            try {
                it.receive(event)
            } catch (Exception e) {
            }
        }
    }
}

interface Subscriber {
    def receive(event)
}

class PublisherSpec extends Specification {
    def pub = new Publisher()
    def sub1 = Mock(Subscriber)
    def sub2 = Mock(Subscriber)

    def setup() {
        pub.subscribers << sub1 << sub2
    }

    def "delivers events to all subscribers"() {
        when:
        pub.send("event")

        then:
        1 * sub1.receive("event")
        1 * sub2.receive("event")
    }

    def "can cope with misbehaving subscribers"() {
        sub1.receive(_) >> { throw new Exception() }

        when:
        pub.send("event1")
        pub.send("event2")

        then:
        1 * sub2.receive("event1")
        1 * sub2.receive("event2")
    }
}

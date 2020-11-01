#!/usr/bin/env groovy
import groovy.util.*

properties(
    [
        buildDiscarder(
            logRotator(
                daysToKeepStr: '3',
                numToKeepStr: '4',
                artifactNumToKeepStr: '3'
            )
        ),
        disableConcurrentBuilds(),
        durabilityHint('PERFORMANCE_OPTIMIZED'),
        timeout(time: 5, unit: 'MINUTES')
    ]
)



timestamps()
triggers {
    pollSCM 'H/5 * * * *'
}

properties([

])

node {
    git url: 'https://github.com/alcool70/pos-arquitetura-devops.git', branch: 'master'

    dir('6-tdd_clean_code'){
        stage('Test') {
            ansiColor('xterm'){
                sh 'ENV=test mvn clean test verify sonar:sonar -Dstyle.color=never'
            }
        }
        stage('Report') {
            echo 'To Be Implemented...'
        }
    }
}

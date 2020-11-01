#!/usr/bin/env groovy
import groovy.util.*

properties([
    disableConcurrentBuilds()
    buildDiscarder(logRotator(numToKeepStr:'10'))
    timeout(time: 5, unit: 'MINUTES')
    timestamps()
    triggers {
        pollSCM 'H/5 * * * *'
    }
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

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
        pipelineTriggers ([
            pollSCM ('H/5 * * * *')
        ])
    ]
)

node {
    timeout(time: 5, unit: 'MINUTES'){
        timestamps{
            ansiColor('xterm'){
                git url: 'https://github.com/alcool70/pos-arquitetura-devops.git', branch: 'master'
                dir('6-tdd_clean_code'){
                    stage('Test') {
                        sh 'ENV=test mvn clean test verify sonar:sonar'
                    }
                    stage('Report') {
                        withCredentials([string(credentialsId: 'codecov', variable: 'CODECOV_TOKEN')]) {
                            echo "$CODECOV_TOKEN"
                        }
                    }
                }
            }
        }
    }
}

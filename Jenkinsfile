node {
    options {
        buildDiscarder(logRotator(numToKeepStr:'10'))
        timeout(time: 5, unit: 'MINUTES')
        ansiColor('xterm')
    }
    git url: 'https://github.com/alcool70/pos-arquitetura-devops.git', branch: 'master'
    dir('6-tdd_clean_code'){
        stage('Test') {
            sh 'ENV=test mvn clean test verify sonar:sonar -Dstyle.color=never'
        }
        stage('Report') {
            echo 'To Be Implemented...'
        }
    }
}
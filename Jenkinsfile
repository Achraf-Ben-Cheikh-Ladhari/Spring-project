pipeline {
    agent  any 
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }
        stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sonar') {
                    sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=target/classes'
                }
            }
        }
    }
}

pipeline {
    agent  any 
    tools {
        jdk 'jdk'
        maven 'maven'
    }
    
    environment {
        SCANNER_HOME = tool 'sonar-qube'
    }
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage("Compile") {
            steps {
                sh "mvn clean compile"
            }
        }
        
        stage("Test Cases") {
            steps {
                sh "mvn test"
            }
        }

        stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sonar') {
                    sh 'mvn clean verify sonar:sonar'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}

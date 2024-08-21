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
                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
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

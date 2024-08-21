pipeline {
    agent any 
    
    tools{
        jdk 'jdk'
        maven 'maven'
    }
    
    environment {
        SCANNER_HOME=tool 'sonar-qube'
    }
    
    stages{
        
        stage("Git Checkout"){
            steps{
                git branch: 'master', changelog: false, poll: false, url: 'https://github.com/Achraf-Ben-Cheikh-Ladhari/Spring-project'
            }
        }
        
        stage("Compile"){
            steps{
                sh "mvn clean compile"
            }
        }
        
         stage("Test Cases"){
            steps{
                sh "mvn test"
            }
        }
        
      stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "${maven}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=springproject"
    }
  }
        
         stage("Build"){
            steps{
                sh " mvn clean install"
            }
        } 
        
    }
}

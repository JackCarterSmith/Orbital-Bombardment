pipeline {
  agent {
    node {
      label 'main'
    }

  }
  stages {
    stage('Setup') {
      steps {
        sh '''chmod +x gradlew
./gradlew setupCIWorkspace'''
      }
    }
    stage('Checking code') {
      steps {
        sh './gradlew check'
      }
    }
    stage('Compile') {
      steps {
        sh './gradlew build'
      }
    }
    stage('JAR release') {
      parallel {
        stage('JAR release') {
          steps {
            sh './gradlew jar'
          }
        }
        stage('Test') {
          steps {
            sh './gradlew test'
          }
        }
      }
    }
  }
  environment {
    JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
  }
}
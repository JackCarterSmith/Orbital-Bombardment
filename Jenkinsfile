pipeline {
  agent {
    node {
      label 'main'
    }

  }
  stages {
    stage('Setup') {
      steps {
        sh './gradlew setupCIWorkspace'
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
      steps {
        sh './gradlew jar'
      }
    }
  }
}
pipeline {
  agent any
  stages {
    stage('Setup') {
      steps {
        sh './gradlew setupCIWorkspace'
      }
    }
    stage('Compilation') {
      steps {
        sh './gradlew build'
      }
    }
    stage('Release') {
      steps {
        echo 'Compilation done.'
      }
    }
  }
}
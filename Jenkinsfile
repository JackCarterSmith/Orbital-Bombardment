pipeline {
  agent {
    node {
      label 'main'
    }

  }
  stages {
    stage('Setup') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew setupCIWorkspace'
      }
    }
    stage('Check') {
      steps {
        sh './gradlew check'
      }
    }
    stage('Compile') {
      steps {
        sh '''./gradlew clean
./gradlew build'''
      }
    }
    stage('JAR release') {
      steps {
        sh './gradlew jar'
        archiveArtifacts(artifacts: 'build/libs/OrbitalSatellite-*.jar', excludes: 'build/libs/OrbitalSatellite-*-sources.jar')
        cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, cleanupMatrixParent: true, deleteDirs: true)
      }
    }
  }
  environment {
    JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
  }
}
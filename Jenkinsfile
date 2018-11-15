pipeline {
  agent {
    docker {
      image 'jackcartersmith/gradle_mc:stable'
      args '-v /var/lib/jenkins/.gradle:/root/.gradle'
    }

  }
  stages {
    stage('Setup') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew setupCIWorkspace'
      }
    }
    stage('Compile') {
      steps {
        sh './gradlew clean'
        sh './gradlew check'
        sh './gradlew build'
      }
    }
    stage('JAR release') {
      steps {
        archiveArtifacts(artifacts: 'build/libs/OrbitalSatellite-*.jar', excludes: 'build/libs/OrbitalSatellite-*-sources.jar')
        cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, cleanupMatrixParent: true, deleteDirs: true)
      }
    }
  }
}
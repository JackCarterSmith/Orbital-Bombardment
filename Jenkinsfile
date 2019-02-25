pipeline {
  agent {
    docker {
      image 'jackcartersmith/gradle_mc:stable_1.12'
    }

  }
  stages {
    stage('Setup') {
      steps {
        sh 'cd /var/lib/jenkins/workspace/Orbital-Satellite_*'
        sh 'chmod u+x gradlew'
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
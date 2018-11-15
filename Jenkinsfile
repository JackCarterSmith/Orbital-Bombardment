pipeline {
  agent {
    docker {
      image 'jackcartersmith/gradle_mc:dev'
    }

  }
  stages {
    stage('Setup') {
      steps {
        sh '''

cd /var/lib/jenkins/workspace/Orbital-Satellite_*'''
        sh 'chmod u+x gradlew'
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
        archiveArtifacts(artifacts: 'build/libs/OrbitalSatellite-*.jar', excludes: 'build/libs/OrbitalSatellite-*-source.jar')
        cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, cleanupMatrixParent: true, deleteDirs: true)
      }
    }
  }
}
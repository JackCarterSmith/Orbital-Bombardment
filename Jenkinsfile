pipeline {
  agent {
    docker {
      image 'jackcartersmith/gradle_mc:latest'
    }

  }
  stages {
    stage('Setup') {
      steps {
        sh '''

cd /var/lib/jenkins/workspace/Orbital-Satellite_*'''
        sh './gradlew setupCIWorkspace'
        sh 'chmod u+x gradlew'
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
        archiveArtifacts(artifacts: '/var/lib/jenkins/workspace/Orbital-Satellite_*/build/libs/OrbitalSatellite-*.jar', excludes: '/var/lib/jenkins/workspace/Orbital-Satellite_*/build/libs/OrbitalSatellite-*-sources.jar')
        sh 'rm -r -f /var/lib/jenkins/workspace/docker-shared/.*'
        cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, cleanupMatrixParent: true, deleteDirs: true)
      }
    }
  }
}
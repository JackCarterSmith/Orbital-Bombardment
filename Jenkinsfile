pipeline {
  agent {
    docker {
      image 'jackcartersmith/gradle_mc:latest'
      args '-v /var/lib/jenkins/workspace/docker-shared:~/root'
    }

  }
  stages {
    stage('Setup') {
      steps {
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
        archiveArtifacts(artifacts: '/var/lib/jenkins/workspace/docker-shared/Orbital-Satellite/build/libs/OrbitalSatellite-*.jar', excludes: '/var/lib/jenkins/workspace/docker-shared/Orbital-Satellite/build/libs/OrbitalSatellite-*-sources.jar')
        sh 'rm -r -f /var/lib/jenkins/workspace/docker-shared/.*'
        cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, cleanupMatrixParent: true, deleteDirs: true)
      }
    }
  }
  environment {
    JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
  }
}
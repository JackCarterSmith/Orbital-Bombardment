pipeline {
  agent {
    docker {
      image 'jackcartersmith/gradle_mc:dev'
    }

  }
  stages {
    stage('Setup') {
      agent {
        docker {
          image 'jackcartersmith/gradle_mc:dev'
        }

      }
      steps {
        sh '''

cd /var/lib/jenkins/workspace/Orbital-Satellite_*'''
        sh 'chmod u+x gradlew'
        sh './gradlew setupCIWorkspace'
      }
    }
    stage('Check') {
      agent {
        docker {
          image 'jackcartersmith/gradle_mc:dev'
        }

      }
      steps {
        sh './gradlew check'
      }
    }
    stage('Compile') {
      agent {
        docker {
          image 'jackcartersmith/gradle_mc:dev'
        }

      }
      steps {
        sh '''./gradlew clean
./gradlew build'''
      }
    }
    stage('JAR release') {
      agent {
        docker {
          image 'jackcartersmith/gradle_mc:dev'
        }

      }
      steps {
        archiveArtifacts(artifacts: 'build/libs/OrbitalSatellite-*.jar', excludes: 'build/libs/OrbitalSatellite-*-source.jar')
        cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenSuccess: true, cleanWhenUnstable: true, cleanupMatrixParent: true, deleteDirs: true)
      }
    }
  }
}
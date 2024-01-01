pipeline {
  agent any
  tools {
    maven 'maven_3.9.6'
  }
  stages {
    stage("build & SonarQube analysis") {
                agent any
                steps {
                  withSonarQubeEnv('sonar') {
                    sh 'mvn clean package sonar:sonar'
                  }
                }
              }
    stage("Quality Gate") {
                steps {
                  timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                  }
                }
              }

    stage ('Deploy') {
      steps {
        script {
          deploy adapters: [tomcat9(credentialsId: 'bcefd924-eebf-4a91-a4f5-4b8cf585737a', path: '', url: 'http://localhost:8080')],
          contextPath: '/DeployWithJenkinsfile', onFailure: false, war: 'webapp/target/*.war'
        }
      }
    }
  }
}

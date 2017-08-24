node {
   stage('Preparation') {
      git 'https://github.com/SalVN/java-project-back.git'
   }
      stage('Clean') {
         sh "mvn -Dmaven.test.failure.ignore clean"
      }
      stage('Package') {
         sh "mvn -Dmaven.test.failure.ignore package"
      }

      stage('Docker Build') {
         sh "docker build -t myapp ."
      }
}
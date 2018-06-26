
import jenkins.model.Jenkins

import com.nirima.jenkins.plugins.docker.KubernetesCloud

if (!Jenkins.instance.clouds.getByName('kubernetes')) {

    KubernetesCloud kubernetesCloud = new KubernetesCloud(
            'kubernetes',
            [],
            'https://192.168.99.100:8443',
            'usine-logicielle',
            'http://172.17.0.6:8080',
            '10',
            0,
            0,
            0
    )

    Jenkins.instance.clouds.add(kubernetesCloud);
    Jenkins.instance.save()

    println 'Configured Kubernetes cloud.'
}

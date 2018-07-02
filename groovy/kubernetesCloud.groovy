import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*

if (!Jenkins.instance.clouds.getByName('kubernetes')) {

    KubernetesCloud kubernetesCloud = new KubernetesCloud(
            'kubernetes',
            [],
	    'https://kubernetes.default.svc.cluster.local',
            'usine-logicielle',
            'http://jenkins-master:8080',
            '10',
            0,
            0,
            0
    );

    kubernetesCloud.setSkipTlsVerify(true);

    PodTemplate podTemplate = new PodTemplate();
    podTemplate.setName('jenkins-slave');
    podTemplate.setLabel('jenkins-slave');

    ContainerTemplate containerTemplate = new ContainerTemplate('jenkins-slave', 'jenkinsci/jnlp-slave');
    containerTemplate.setTtyEnabled(true);
    podTemplate.setContainers([containerTemplate]);

    kubernetesCloud.addTemplate(podTemplate);

    Jenkins.instance.clouds.add(kubernetesCloud);
    Jenkins.instance.save();

    println 'Configured Kubernetes cloud.';
}

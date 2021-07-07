import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureTests {
    @Test
    public void CheckServiceAnnotationInPackage() {
        JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupoD.backenddesapptp");
        ArchRule rule = classes().that().areAnnotatedWith(Service.class).should().resideInAPackage("..service..");
        rule.check(classes);
    }

    @Test
    public void CheckServiceClassNameInPackage() {
        JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupoD.backenddesapptp");
        ArchRule rule = classes().that().haveNameMatching(".*Service").should().resideInAPackage("..service..");
        rule.check(classes);
    }
    //Controllers...

    @Test
    public void CheckLayersAccess() {
        JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupoD.backenddesapptp");
        Architectures.LayeredArchitecture architecture = layeredArchitecture()
                .layer("Controller").definedBy("..webService..")
                .layer("Model").definedBy("..model..")
                .layer("Persistence").definedBy("..persistence..")
                .layer("Security").definedBy("..security..")
                .layer("Service").definedBy("..service..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Model", "Security", "Service")
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Security");
        architecture.check(classes);
    }

}

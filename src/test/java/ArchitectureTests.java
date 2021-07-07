import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

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
    //Controller

}

package org.gnube.dnd.test;

import org.gnube.dnd.api.Weapon;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class YamlTest {

    @Test
    public void testYaml() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("longsword.yaml");
        Yaml yaml = new Yaml(new Constructor(Weapon.class));
        Weapon weapon = yaml.load(is);
        System.out.println("done");
    }

}

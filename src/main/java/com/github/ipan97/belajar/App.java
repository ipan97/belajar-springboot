/*
 * Copyright 2016 Pivotal Software, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ipan97.belajar;

import com.github.ipan97.belajar.entity.Developer;
import com.github.ipan97.belajar.entity.Skill;
import com.github.ipan97.belajar.repository.DeveloperRepository;
import com.github.ipan97.belajar.repository.SkillRepository;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author ipan97
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private SkillRepository skillRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Skill javascript = new Skill("javascript", "Javascript language skill");
        Skill ruby = new Skill("ruby", "Ruby language skill");
        Skill emberjs = new Skill("emberjs", "Emberjs framework");
        Skill angularjs = new Skill("angularjs", "Angularjs framework");

        skillRepository.save(javascript);
        skillRepository.save(ruby);
        skillRepository.save(emberjs);
        skillRepository.save(angularjs);

        List<Developer> developers = new LinkedList<Developer>();
        developers.add(new Developer("John", "Smith", "john.smith@example.com",
                Arrays.asList(new Skill[]{javascript, ruby})));
        developers.add(new Developer("Mark", "Johnson", "mjohnson@example.com",
                Arrays.asList(new Skill[]{emberjs, ruby})));
        developers.add(new Developer("Michael", "Williams", "michael.williams@example.com",
                Arrays.asList(new Skill[]{angularjs, ruby})));
        developers.add(new Developer("Fred", "Miller", "f.miller@example.com",
                Arrays.asList(new Skill[]{emberjs, angularjs, javascript})));
        developers.add(new Developer("Bob", "Brown", "brown@example.com",
                Arrays.asList(new Skill[]{emberjs})));
        developerRepository.save(developers);
    }
}

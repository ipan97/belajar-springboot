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
package com.github.ipan97.belajar.controller;

import com.github.ipan97.belajar.entity.Developer;
import com.github.ipan97.belajar.entity.Skill;
import com.github.ipan97.belajar.repository.DeveloperRepository;
import com.github.ipan97.belajar.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ipan97
 */
@Controller
public class DevelopersController {

    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping(value = "/developer/{id}")
    public String developer(@PathVariable Long id, Model model) {
        model.addAttribute("developer", developerRepository.findOne(id));
        model.addAttribute("skills", skillRepository.findAll());
        return "developer";
    }

    @RequestMapping(value = "/developers", method = RequestMethod.GET)
    public String developersList(Model model) {
        model.addAttribute("developers", developerRepository.findAll());
        return "developers";
    }

    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public String developersAdd(
            @RequestParam String email, @RequestParam String firstName, @RequestParam String lasatName, Model model) {
        Developer d = new Developer();
        d.setFirstName(firstName);
        d.setLastName(lasatName);
        d.setEmail(email);
        developerRepository.save(d);
        model.addAttribute("developers", d);
        model.addAttribute("skills", skillRepository.findAll());
        return "redirect:/developer/" + d.getId();
    }

    @RequestMapping(value = "/developer/{id}/skills", method = RequestMethod.POST)
    public String developersAddSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
        Skill skill = skillRepository.findOne(skillId);
        Developer developer = developerRepository.findOne(id);
        if (developer != null) {
            if (developer.hasSkill(skill)) {
                developer.getSkills().add(skill);
            }
            developerRepository.save(developer);
            model.addAttribute("developer", developerRepository.findAll());
            model.addAttribute("skill", skillRepository.findAll());
            return "redirect:/developer/" + developer.getId();
        }
        model.addAttribute("developers", developerRepository.findAll());
        return "redirect:/developers";
    }

}

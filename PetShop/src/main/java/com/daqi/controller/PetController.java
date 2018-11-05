package com.daqi.controller;

import com.daqi.dao.PetMapper;
import com.daqi.entity.Pet;
import com.daqi.vo.FormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetMapper petMapper;

    //    显示全部的方法
    @RequestMapping(method = RequestMethod.GET)
    public String selectAll(Model model) {
        List<Pet> petList = petMapper.selectAll();
        model.addAttribute("petList", petList);
        return "index";
    }

    //    添加的方法
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(FormBean formBean) {
        int c = petMapper.add(formBean.getPets());
        return "redirect:/pet";
    }


    //    删除的方法
    @RequestMapping("/del/{petid}")
    public String del(@PathVariable("petid") int petid) {
        petMapper.deleteByPrimaryKey(petid);
        return "redirect:/pet";
    }


//    修改的方法
    @RequestMapping("/update/{petid}")
    public String update(@PathVariable("petid") Pet pets){
        petMapper.updateByPrimaryKey(pets);
        return "";
    }
}

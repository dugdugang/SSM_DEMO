package com.dg.ssm.controller;

import com.dg.ssm.po.Goods;
import com.dg.ssm.po.ItemCustom;
import com.dg.ssm.po.Items;
import com.dg.ssm.po.ItemsQueryVo;
import com.dg.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/items")
public class ItemsController {
    //注入service
    @Autowired
    private ItemService itemService;

    @RequestMapping("/queryItems")
    public ModelAndView queryItem() throws Exception {
        //调用service来查询商品列表
        List<ItemCustom> itemCustoms = itemService.findItemsList(null);
        System.out.println((itemCustoms == null) + " " + itemCustoms.size());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemCustoms);
        modelAndView.setViewName("itemsList");
        return modelAndView;
    }

    //    @RequestMapping(value = "/editItem", method = RequestMethod.GET)
//    public ModelAndView editItem(int id, Model model) throws Exception {
//        System.out.println(id+"");
//        Items itemCustom = itemService.findItemCustom(id);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("itemCustom", itemCustom);
//        modelAndView.setViewName("editItem");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/editItem", method = RequestMethod.GET)
//    public String  editItem(int id, Model model) throws Exception {
//        System.out.println(id+"");
//        Items itemCustom = itemService.findItemCustom(id);
//
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.addObject("itemCustom", itemCustom);
////        modelAndView.setViewName("editItem");
//        model.addAttribute("itemCustom", itemCustom);
//        return "editItem";
//    }
//
//    @RequestMapping(value = "/editItem", method = RequestMethod.GET)
//    public void   editItem(int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println(id+"");
//        Items itemCustom = itemService.findItemCustom(id);
//        request.setAttribute("itemCustom",itemCustom);
//        request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp").forward(request,response);
//    }
    @RequestMapping(value = "/editItem/{id}", method = RequestMethod.GET)
    public void editItems(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = false, defaultValue = "1") @PathVariable("id") Integer ssid) throws Exception {

        //调用service查询商品的信息
        Items itemsCustom = itemService.findItemCustom(ssid);

        request.setAttribute("itemCustom", itemsCustom);

        //zhuyi如果使用request转向页面，这里需要指定页面的完整路径
        request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp").forward(request, response);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitItem(ItemCustom itemCustom) throws Exception {
//        System.out.println("id:"+id);
        itemService.updateItemCustom(itemCustom.getId(), itemCustom);
        System.out.println("pojo:" + itemCustom);
        return "redirect:queryItems";//重定向
    }

    /**
     * 测试pojo对象绑定
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(Model model, Goods goods) {
        System.out.println(goods);
        model.addAttribute("name", "sssss");
        return "test";
    }

    /**
     * 访问test页面
     */
    @RequestMapping(value = "/go")
    public String go(Model model) {
        model.addAttribute("date", new Date());
        return "test";
    }
//    //自定义属性编辑器,无法在多个controller共用
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        //Date.class 必须食欲controller方法形参pojo属性一致的date类型 java.util.Date
//        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"),true));
//    }

    @RequestMapping(value = "/show")
    public String show(Model model, String name, String password) {
//        model.addAttribute("name", "re" + name);
//        model.addAttribute("password", "re" + password);
        return "datareshow";
    }

    @ModelAttribute("map")
    public Map<String, String> show() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "nihao");
        map.put("password", "asdsafasf");
        return map;
    }

    //删除商品
    @RequestMapping("/deleteItems")
    public String deleteitems(Integer[] delete_id) throws Exception {
        System.out.println(delete_id + " " + delete_id.length);
        return "queryItems";
    }

    @RequestMapping("/editItemsList")
    public ModelAndView editItemsList() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<ItemCustom> itemsList = itemService.findItemsList(null);
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("itemList1");
        return modelAndView;
    }

    @RequestMapping("/editItemsListSubmit")
    public String editItemsListSubmit(ItemsQueryVo itemsQueryVo) throws Exception {

        System.out.println("size:" + itemsQueryVo.getItemsList().size());
        for (ItemCustom itemCustom : itemsQueryVo.getItemsList())
            System.out.println(itemCustom);

        return "success";
    }

    @RequestMapping(value = "/uploadImag", method = RequestMethod.POST)
    public String uploadImage(Model model, MultipartFile pictureFile) throws IOException {
        //进行图片上传
        if (pictureFile != null && pictureFile.getOriginalFilename() != null && pictureFile.getOriginalFilename().length() > 0) {
            //图片上传成功后，将图片地址写入数据库
            String filePath = "C:\\Users\\Administrator\\Desktop\\picture\\";
            String fileName = pictureFile.getOriginalFilename();
            String newFileName = UUID.randomUUID() + fileName.substring(fileName.indexOf("."));
            //新文件
            File file = new File(filePath + newFileName);
            //将内存中的文件写入磁盘
            pictureFile.transferTo(file);
            //图片上传成功
            System.out.println("图片上传成功!" + file.getAbsolutePath());
            model.addAttribute("img", newFileName);
        }
        return "forward:/items/up";
    }


    @RequestMapping("/up")
    public String upload() {
        System.out.println("xxxxxxx");
        return "upload";
    }


    @RequestMapping(value = "/requestJson", method = RequestMethod.POST)
    public @ResponseBody
    ItemCustom requestjson(@RequestBody ItemCustom itemCustom) {
        System.out.println("asdasdas");
        return itemCustom;
    }

    @RequestMapping(value = "/testAjax", method = RequestMethod.POST)
    public String request() {
        System.out.println("success");
        return "";
    }

    //请求key/value(在页面中通过ajax写入用户想要请求的key/value信息，不需要加@RequestBody注解)，响应json(由于action返回的是itemsCustom对象，所以需要加入@ResponseBody注解将pojo对象转换为json格式响应给用户)
    @RequestMapping("/responseJson")
    public @ResponseBody
    ItemCustom responseJson(ItemCustom itemsCustom) throws Exception {
        System.out.println("yyyyy");
        return itemsCustom;
    }


    //商品提交页面
    //itemsQueryVo是包装类型的pojo
    //在@Validated中定义使用ValidGroup1组下边的校验
    @RequestMapping("/editItemSubmit")
    public String editItemSubmit(Model model, Integer id,
                                 @Validated @ModelAttribute(value = "itemsCustom") ItemCustom itemsCustom,
                                 BindingResult bindingResult,
                                 //上传图片
                                 MultipartFile pictureFile
    ) throws Exception {
        //输出校验错误信息
        //如果参数绑定时出错
        if (bindingResult.hasErrors()) {
            //获取错误
            List<ObjectError> errors = bindingResult.getAllErrors();

            model.addAttribute("errors", errors);

            for (ObjectError error : errors) {
                //输出错误信息
                System.out.println(error.getDefaultMessage());
            }

            //如果校验错误，仍然回到商品修改页面
            return "editItem";

        }
        return "";
    }

    //用户登陆提交方法
    @RequestMapping("/login")
    public String login(HttpSession session,String usercode, String password) throws Exception
    {
        //调用service校验用户帐号和密码的正确性
        //这个东西我们讲shiro的时候再写



        //如果service校验通过，将用户身份记录到session
        session.setAttribute("usercode",usercode);

        //重定向到商品查询页面
        return "redirect:/items/queryItems.action";
    }

    //用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception
    {
        //session失效
        session.invalidate();

        //重定向到商品查询页面
        return "redirect:/items/queryItems.action";
    }

}

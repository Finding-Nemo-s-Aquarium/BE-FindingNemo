package org.nimo.aquarium.controller;

import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.config.auth.PrincipalDetails;
import org.nimo.aquarium.domain.item.Item;
import org.nimo.aquarium.domain.sale.Sale;
import org.nimo.aquarium.domain.saleitem.SaleItem;
import org.nimo.aquarium.service.ItemService;
import org.nimo.aquarium.service.SaleService;
import org.nimo.aquarium.service.UserPageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SellerPageController {

    private final UserPageService userPageService;
    private final ItemService itemService;
    private final SaleService saleService;

    @GetMapping("/seller/{id}")
    public String sellerPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails.getUser().getId() == id) {
            model.addAttribute("user", userPageService.findUser(id));

            return "/seller/sellerPage";
        } else {
            return "redirect:/main";
        }

    }

    @GetMapping("/seller/manage/{id}")
    public String itemManage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getId() == id) {
            List<Item> allItem = itemService.allItemView();
            List<Item> userItem = new ArrayList<>();

            for(Item item : allItem) {
                if(item.getSeller().getId() == id) {
                    userItem.add(item);
                }
            }

            model.addAttribute("seller", userPageService.findUser(id));
            model.addAttribute("userItem", userItem);

            return "seller/itemManage";
        } else {
            return "redirect:/main";
        }
    }

    @GetMapping("/seller/salelist/{id}")
    public String saleList(@PathVariable("id")Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails.getUser().getId() == id) {

            Sale sales = saleService.findSaleById(id);
            List<SaleItem> saleItemList = saleService.findSellerSaleItems(id);

            model.addAttribute("sales", sales);
            model.addAttribute("totalCount", sales.getTotalCount());
            model.addAttribute("sellerSaleItems", saleItemList);
            model.addAttribute("seller", userPageService.findUser(id));

            return "seller/saleList";
        }
        else {
            return "redirect:/main";
        }
    }
}

package org.nimo.aquarium.controller;

import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.config.auth.PrincipalDetails;
import org.nimo.aquarium.domain.cart.Cart;
import org.nimo.aquarium.domain.cartitem.CartItem;
import org.nimo.aquarium.domain.item.Item;
import org.nimo.aquarium.domain.user.User;
import org.nimo.aquarium.service.CartService;
import org.nimo.aquarium.service.ItemService;
import org.nimo.aquarium.service.UserPageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final UserPageService userPageService;
    private final CartService cartService;

    @GetMapping("/")
    public String mainPageNoneLogin(Model model) {
        List<Item> items = itemService.allItemView();
        model.addAttribute("items", items);

        return "main";
    }

    @GetMapping("/main")
    public String mainPage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            int sellerId = principalDetails.getUser().getId();
            List<Item> items = itemService.allItemView();
            model.addAttribute("items", items);
            model.addAttribute("user", userPageService.findUser(sellerId));

            return "/main";
        } else {
            int userId = principalDetails.getUser().getId();
            List<Item> items = itemService.allItemView();
            model.addAttribute("items", items);
            model.addAttribute("user", userPageService.findUser(userId));

            return "/main";
        }
    }

    @GetMapping("/item/new")
    public String itemSaveForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            model.addAttribute("user", principalDetails.getUser());
            return "/seller/itemForm";
        } else {
            return "redirect:/main";
        }
    }

    @PostMapping("/item/new/pro")
    public String itemSave(Item item, @AuthenticationPrincipal PrincipalDetails principalDetails, MultipartFile imgFile) throws Exception {
        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            item.setSeller(principalDetails.getUser());
            itemService.saveItem(item, imgFile);

            return "redirect:/main";
        } else {
            return "redirect:/main";
        }
    }

    @GetMapping("/item/modify/{id}")
    public String itemModifyForm(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            User user = itemService.itemView(id).getSeller();
            if(user.getId() == principalDetails.getUser().getId()) {

                model.addAttribute("item", itemService.itemView(id));
                model.addAttribute("user", principalDetails.getUser());

                return "/seller/itemModify";
            } else {
                return "redirect:/main";
            }
        } else {
            return "redirect:/main";
        }
    }

    @PostMapping("/item/modify/pro/{id}")
    public String itemModify(Item item, @PathVariable("id") Integer id, @AuthenticationPrincipal PrincipalDetails principalDetails, MultipartFile imgFile) throws Exception{
        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            User user = itemService.itemView(id).getSeller();

            if(user.getId() == principalDetails.getUser().getId()) {
                itemService.itemModify(item, id, imgFile);

                return "redirect:/main";
            } else {
                return "redirect:/main";
            }
        } else {
            return "redirect:/main";
        }
    }

    @GetMapping("/item/view/{itemId}")
    public String ItemView(Model model, @PathVariable("itemId") Integer id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            User user = principalDetails.getUser();

            model.addAttribute("item", itemService.itemView(id));
            model.addAttribute("user", user);

            return "itemView";
        } else {
            User user = principalDetails.getUser();

            User loginUser = userPageService.findUser(user.getId());

            int cartCount = 0;
            Cart userCart = cartService.findUserCart(loginUser.getId());
            List<CartItem> cartItems = cartService.allUserCartView(userCart);

            for(CartItem cartItem : cartItems) {
                cartCount += cartItem.getCount();
            }

            model.addAttribute("cartCount", cartCount);
            model.addAttribute("item", itemService.itemView(id));
            model.addAttribute("user", user);

            return "itemView";
        }
    }

    @GetMapping("/item/view/nonlogin/{id}")
    public String nonLoginItemView(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("item", itemService.itemView(id));
        return "itemView";

    }

    @GetMapping("/item/delete/{id}")
    public String itemDelete(@PathVariable("id") Integer id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            User user = itemService.itemView(id).getSeller();

            if(user.getId() == principalDetails.getUser().getId()) {
                itemService.itemDelete(id);

                return "redirect:/main";
            } else {
                return "redirect:/main";
            }
        } else {
            return "redirect:/main";
        }
    }

    @GetMapping("/item/list")
    public String itemList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                           String searchKeyword, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userPageService.findUser(principalDetails.getUser().getId());

        Page<Item> items = null;

        if (searchKeyword == null) {
            items = itemService.allItemViewPage(pageable);
        } else {
            items = itemService.itemSearchList(searchKeyword, pageable);
        }

        int nowPage = items.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, items.getTotalPages());

        model.addAttribute("items", items);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("user", user);

        return "itemList";
    }

    @GetMapping("/nonlogin/item/list")
    public String itemList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                           String searchKeyword) {

        Page<Item> items = null;

        if (searchKeyword == null) {
            items = itemService.allItemViewPage(pageable);
        } else {
            items = itemService.itemSearchList(searchKeyword, pageable);
        }

        int nowPage = items.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, items.getTotalPages());

        model.addAttribute("items", items);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "itemList";
    }
}

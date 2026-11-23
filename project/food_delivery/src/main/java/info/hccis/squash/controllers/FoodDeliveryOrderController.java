package info.hccis.squash.controllers;

import info.hccis.squash.bo.FoodDeliveryOrderBO;
import info.hccis.squash.jpa.entity.FoodDeliveryOrder;
import info.hccis.squash.repositories.FoodDeliveryOrderRepository;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to administer food delivery orders (list, add, edit, delete).
 * Based on the class example controllers (TicketOrderController and
 * SkillsAssessmentController) from the starting project.
 *
 * @author Harry Abraham
 * @since 20261123
 * Issue#6
 */
@Controller
@RequestMapping("/fooddeliveryorder")
public class FoodDeliveryOrderController {

    private final FoodDeliveryOrderRepository _fdor;

    private static final Logger logger = LoggerFactory.getLogger(FoodDeliveryOrderController.class);

    /**
     * Constructor. Spring passes in the repository automatically (@Autowired).
     *
     * @param fdor repository used to access food delivery orders
     * @since 20261123
     * @author Harry Abraham
     */
    @Autowired
    public FoodDeliveryOrderController(FoodDeliveryOrderRepository fdor) {
        _fdor = fdor;
    }

    /**
     * List page. Loads all orders from the database and sends the user to
     * the list view.
     *
     * @param model used to pass the orders to the view
     * @return list view
     * @since 20261123
     * @author Harry Abraham
     */
    @RequestMapping("")
    public String home(Model model) {

        Iterable<FoodDeliveryOrder> theList = _fdor.findAll();
        model.addAttribute("foodDeliveryOrders", theList);

        logger.info("There are " + _fdor.count() + " food delivery orders in the database");
        return "fooddeliveryorder/list";
    }

    /**
     * Add page. Sends the user to the add/edit view with an empty order.
     *
     * @param model used to pass the empty order to the view
     * @return add view
     * @since 20261123
     * @author Harry Abraham
     */
    @RequestMapping("/add")
    public String add(Model model) {

        FoodDeliveryOrder foodDeliveryOrder = new FoodDeliveryOrder();
        model.addAttribute("foodDeliveryOrder", foodDeliveryOrder);

        return "fooddeliveryorder/add";
    }

    /**
     * Submit method that processes the add and edit form. If the validation
     * fails, the user is sent back to the form to see the error messages.
     * Otherwise the delivery fee and total cost are calculated and the order
     * is saved (a new row for add, an update of the existing row for edit).
     *
     * @param model used to pass data to the view
     * @param foodDeliveryOrder order being added or modified
     * @param bindingResult result of the validation
     * @return add view if there are errors, otherwise back to the list
     * @since 20261123
     * @author Harry Abraham
     */
    @RequestMapping("/submit")
    public String submit(Model model, @Valid @ModelAttribute("foodDeliveryOrder") FoodDeliveryOrder foodDeliveryOrder, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.info("Validation error - sending the user back to the form");
            return "fooddeliveryorder/add";
        }

        FoodDeliveryOrderBO foodDeliveryOrderBO = new FoodDeliveryOrderBO();
        foodDeliveryOrderBO.processCost(foodDeliveryOrder);

        _fdor.save(foodDeliveryOrder);
        return "redirect:/fooddeliveryorder";
    }

    /**
     * Edit page. Finds the order with the given id and sends the user to the
     * add/edit view with that order's details filled in.
     *
     * @param id id of the order to edit
     * @param model used to pass the order to the view
     * @return add view if the order is found, otherwise back to the list
     * @since 20261123
     * @author Harry Abraham
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Optional<FoodDeliveryOrder> foodDeliveryOrderOptional = _fdor.findById(id);
        if (foodDeliveryOrderOptional.isPresent()) {
            model.addAttribute("foodDeliveryOrder", foodDeliveryOrderOptional.get());
            return "fooddeliveryorder/add";
        }

        //Order not found for this id, go back to the list.
        return "redirect:/fooddeliveryorder";
    }

    /**
     * Delete the order with the given id and go back to the list.
     *
     * @param id id of the order to delete
     * @return directions to the list page
     * @since 20261123
     * @author Harry Abraham
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {

        if (_fdor.existsById(id)) {
            _fdor.deleteById(id);
        }
        return "redirect:/fooddeliveryorder";
    }
}
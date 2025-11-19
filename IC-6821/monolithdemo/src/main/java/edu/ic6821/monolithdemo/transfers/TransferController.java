package edu.ic6821.monolithdemo.transfers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferController {

    private static final String VIEW_TRANSFER = "transferView";
    private static final String ATTRIBUTE_TRANSFER_DTO = "transferDTO";
    private static final String ATTRIBUTE_TRANSFER_RESULT_DTO = "transferResultDTO";

    private final TransferService transferService;

    public TransferController(final TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/transfer")
    public String render(Model model) {
        model.addAttribute(ATTRIBUTE_TRANSFER_DTO, new TransferDTO("", "", 0.0));
        return VIEW_TRANSFER;
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute TransferDTO transferDTO, Model model) {
        Boolean success = transferService.transfer(
                transferDTO.fromAccount(),
                transferDTO.toAccount(),
                transferDTO.amount()
        );
        model.addAttribute(ATTRIBUTE_TRANSFER_RESULT_DTO, new TransferResultDTO(success));
        return VIEW_TRANSFER;
    }

}

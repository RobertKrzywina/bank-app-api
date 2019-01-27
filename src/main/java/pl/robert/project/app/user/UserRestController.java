package pl.robert.project.app.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.app.transaction.domain.dto.ReadUserTransactionsDto;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.transaction.query.ReadUserReceivedTransactionsQueryDto;
import pl.robert.project.app.transaction.query.ReadUserSentTransactionsQueryDto;
import pl.robert.project.app.transaction.query.ReadUserTransactionsQueryDto;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.ChangeUserPasswordDto;
import pl.robert.project.app.user.query.AboutMeUserQueryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/user-panel")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
class UserRestController {

    private UserFacade userFacade;

    @GetMapping("/about-me")
    public ResponseEntity aboutMe(Authentication auth) {
        AboutMeUserQueryDto aboutMeQuery = userFacade.aboutMe(auth);

        if (aboutMeQuery == null) {
            return ResponseEntity.status(404).body(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(200).body(aboutMeQuery);
    }

    @GetMapping("/transactions")
    public ResponseEntity allTransactions(Authentication auth,
                                          ReadUserTransactionsDto dto, BindingResult result) {
        List<ReadUserTransactionsQueryDto> transactionsQuery =
                userFacade.getAllUserTransactions(auth, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(transactionsQuery);
    }

    @GetMapping("/transactions/sent")
    public ResponseEntity sentTransactions(Authentication auth,
                                           ReadUserTransactionsDto dto, BindingResult result) {
        List<ReadUserSentTransactionsQueryDto> sentTransactionsQuery =
                userFacade.getAllUserSentTransactions(auth, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(sentTransactionsQuery);
    }

    @GetMapping("/transactions/received")
    public ResponseEntity receivedTransactions(Authentication auth,
                                               ReadUserTransactionsDto dto, BindingResult result) {
        List<ReadUserReceivedTransactionsQueryDto> receivedTransactionsQuery =
                userFacade.getAllUserReceivedTransactions(auth, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(receivedTransactionsQuery);
    }

    @PostMapping("/send-transaction")
    public ResponseEntity sendTransaction(Authentication auth,
                                          @RequestBody @Valid SendTransactionDto dto, BindingResult result) {
        userFacade.sendTransaction(auth, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(Authentication auth,
                                         @RequestBody @Valid ChangeUserPasswordDto dto, BindingResult result) {
        userFacade.changePassword(auth.getName(), dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dto.getNewPassword());
    }
}

package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Model.DTO.EmailRequest;
import by.lyofchik.AppSpring.Service.Mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
// Не работает
public class EmailSenderRestController {

    private final MailService mailService;

    @GetMapping
    public void sendEmail(@RequestParam String email, @RequestParam String message, @RequestParam byte[] data) {
//        var request = EmailRequest.builder()
//                .email(email)
//                .message(message)
//                //.document(data)
//                .build();
//        mailService.send(request);
    }

    @PostMapping
    public ResponseEntity<Object> adminPost(@ModelAttribute EmailRequest emailRequest){
        log.info("Received request for email: {}", emailRequest.getEmail());
        mailService.send(emailRequest);
        return null;
    }
}

//    @PostMapping
//    public CommonResponse<EmailResponseDto> send(@RequestBody EmailRequest email) {
////        try {
////            mailService.sendEmail(email)
////                    .map(emailResponseDto -> CommonResponse.builder()
////                            .data(emailResponseDto)
////                            .errorCode("0")
////                            .errorDescription("Успешно"))
////                    .orElseThrow(() -> new CustomException("нЕ УДАЛОСЬ ОТПРАВИТЬ СООЩЕНИЕ"));
////
////
////            mailService.sendEmail(email)
////                    .map(emailResponseDto -> CommonResponse.builder()
////                            .data(emailResponseDto)
////                            .errorCode("0")
////                            .errorDescription("Успешно"))
////                    .orElseThrow(() -> new CustomException("нЕ УДАЛОСЬ ОТПРАВИТЬ СООЩЕНИЕ"));
////
////
////            Arrays.asList(1,2,3,4,2).stream().filter(t->t.equals(2)).map()
////            mailService.sendEmail(email)
////                    .map(emailResponseDto -> CommonResponse.builder()
////                            .data(emailResponseDto)
////                            .errorCode("0")
////                            .errorDescription("Успешно"))
////                    .orElseThrow(() -> new CustomException("нЕ УДАЛОСЬ ОТПРАВИТЬ СООЩЕНИЕ"));
////
////            sdgdfgdfg
////        } catch (CustomException e) {
////            throw new RuntimeException(e);
////        }catch (E)}
//
//}

package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import upc.fib.pes.grup121.dto.ChatDTO
import upc.fib.pes.grup121.service.ChatService

@RestController
class ChatController(
    private final var chatService: ChatService
) {
    @GetMapping("chat")
    fun getChatByFriendship(@RequestParam friendshipId: Long): ResponseEntity<ChatDTO> {
        var chat: ChatDTO? = chatService.getChatByFriendship(friendshipId)
        chat?.let{
            return ResponseEntity.ok(chat)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }

    @GetMapping("chats")
    fun getAllChats(@RequestParam userId: Long): ResponseEntity<List<ChatDTO>> {
        var chats: List<ChatDTO>? = chatService.getAllChats(userId)
        chats.let{
            return ResponseEntity.ok(it)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }

    @PostMapping("chat")
    fun insertChat(@RequestBody chat: ChatDTO){
        chat.let{
            chatService.insertChat(it);
        }
    }

    @DeleteMapping("chat")
    fun deleteChat(@RequestBody chatId: Long){
        val nameUser: String = SecurityContextHolder.getContext().authentication.name
        chatService.deleteChat(chatId, nameUser)
    }
}
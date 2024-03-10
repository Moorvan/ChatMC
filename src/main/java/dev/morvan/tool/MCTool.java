package dev.morvan.tool;

import dev.langchain4j.agent.tool.Tool;
import dev.morvan.client.MCClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Request;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Slf4j
public class MCTool {

    @RestClient
    MCClient mcClient;

    @Tool("call this function for to run model checking with vmt content")
    public String modelChecking(String vmtContent) {
        log.info("Model checking with vmt content: {}", vmtContent);
        return mcClient.check(vmtContent);
    }

    @Tool("call this function to parser Chisel module to vmt format")
    public String parseFromChiselToVmt(String chiselContent) {
        log.info("parse chisel content: {}", chiselContent);
        return """
                ; Counter Module
                (declare-const clock Bool) ; Clock signal
                (declare-const reset Bool) ; Reset signal
                                
                (declare-const max_val (_ BitVec 8)) ; max_val variable, 8 bit wide
                                
                (declare-const count (_ BitVec 8)) ; count variable, 8 bit wide
                (declare-const count.next (_ BitVec 8))
                (define-fun sv.count () (_ BitVec 8) (! count :next count.next))
                                
                (define-fun sv.io_out () (_ BitVec 8) count) ; io.out is count
                (define-fun get_count () (_ BitVec 8) count)
                                
                (define-fun next_count () (_ BitVec 8) (bvadd get_count  #b00000001)) ; define next count value
                                
                ; Initialise the count to 0
                (define-fun init () Bool (!
                (= count #b00000000)
                :init true))
                                
                ; Counter Transition System
                (define-fun trans () Bool (!
                (ite
                reset
                (= count.next #b00000000)
                (= count.next (ite (= count max_val) #b00000000 next_count))
                )
                :trans true
                ))
                """;
    }
}

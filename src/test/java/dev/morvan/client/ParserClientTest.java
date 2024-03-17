package dev.morvan.client;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ParserClientTest {
    @RestClient
    ParserClient parserClient;

    @Test
    void testParser() {
        System.out.println(parserClient.parseFromChiselToVmt("""
                class Counter(max_val: Int) extends Module {
                  val io = IO(new Bundle {
                    val count = Output(UInt(8.W))
                  })
                  val count = RegInit(0.U(8.W))
                  count := count + 1.U
                  when(count === max_val.U) {
                    count := 0.U
                  }
                  io.count := count
                }
                """));
    }

}

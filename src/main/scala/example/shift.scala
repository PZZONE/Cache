package cache

import chisel3._
import chisel3.stage.ChiselGeneratorAnnotation
import circt.stage.{ChiselStage,FirtoolOption}
import chisel3.util._

class cache extends Module {
    val io = IO(new Bundle{
        val data = Input(UInt(32.W))
        val shift = Input(UInt(5.W))
        val out = Output(UInt(32.W))
    })

    io.out := io.data << io.shift
}

object Generate extends App {
    (new ChiselStage).execute(
        Array("--target","systemverilog","--target-dir","./build"),
        Seq(ChiselGeneratorAnnotation(() => new shiftmodule()),
        FirtoolOption("--disable-all-randomization"))
    )
}
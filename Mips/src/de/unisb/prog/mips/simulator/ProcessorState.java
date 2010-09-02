package de.unisb.prog.mips.simulator;


public class ProcessorState {
	
	public int[] gp;
	public float[] fp;
	public int pc, lo, hi;
	boolean fpc;
	
	public ProcessorState clone() {
		return new ProcessorState() {
			{
				this.gp = ProcessorState.this.gp.clone();
				this.fp = ProcessorState.this.fp.clone();
				this.pc = ProcessorState.this.pc;
				this.lo = ProcessorState.this.lo;
				this.hi = ProcessorState.this.hi;
				this.fpc = ProcessorState.this.fpc;
			}
		};
	}

}

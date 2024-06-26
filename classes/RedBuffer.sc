// redFrik 050329
// load a segment from a long soundfile.  returns a buffer

RedBuffer {

	//offset in percent and length in seconds
	*new {|server, path, segmentOffset= 0, segmentLength= 10|
		var sf, offsetFrames, lengthFrames;
		sf= SoundFile(path).info;
		if(sf.isNil, {
			("RedBuffer: file "++path++" not found").warn;
			this.halt;
		});
		offsetFrames= (segmentOffset*sf.numFrames).round;
		lengthFrames= (segmentLength*sf.sampleRate).round;
		if(offsetFrames+lengthFrames>sf.numFrames, {
			"RedBuffer: selected segment out of buffer bounds".warn;
		});
		^Buffer.read(
			server,
			path,
			offsetFrames,
			lengthFrames,
			{("RedBuffer: done loading segment from "++path).postln}
		);
	}
}

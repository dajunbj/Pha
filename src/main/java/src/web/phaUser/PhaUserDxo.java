package src.web.phaUser;

import src.entity.PhaUser;

public interface PhaUserDxo {

	public PhaUser convert(AbstractPhaUserPage src);
	
	public void convert(PhaUser src, AbstractPhaUserPage dest);
}
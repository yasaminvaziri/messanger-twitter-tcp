package shared.events.friendsEvent;

import client.graphic.friendLists.BlockList;

public class SendBlockListEvent {
    private BlockList blockList;

    public SendBlockListEvent(BlockList blockList) {
        this.blockList = blockList;
    }

    public BlockList getBlockList() {
        return blockList;
    }

    public void setBlockList(BlockList blockList) {
        this.blockList = blockList;
    }
}

package org.capstone;

import org.capstone.models.*;

public class DataHelper {

    public static DiscordUser makeDiscordUser(long discordUserId, String discordUsername) {
        DiscordUser discordUser = new DiscordUser();
        discordUser.setDiscordUserId(discordUserId);
        discordUser.setUsername(discordUsername);

        return discordUser;
    }
    public static DiscordServer makeDiscordServer(long discordServerId, String discordServerName) {
        DiscordServer discordServer = new DiscordServer();
        discordServer.setDiscordServerId(discordServerId);
        discordServer.setServername(discordServerName);

        return discordServer;
    }

    public static Clip makeClip(
            int clipId, String clipName, String youtubeId,
            double startTime, double endTime,
            double volume, double playbackSpeed,
            long discordUserId) {
        Clip clip = new Clip();
        clip.setClipId(clipId);
        clip.setClipName(clipName);
        clip.setYoutubeId(youtubeId);
        clip.setStartTime(startTime);
        clip.setEndTime(endTime);
        clip.setVolume(volume);
        clip.setPlaybackSpeed(playbackSpeed);
        clip.setDiscordUserId(discordUserId);

        return clip;
    }

    public static Playlist makePlaylist(int playlistId, String playlistName, long discordUserId) {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(playlistId);
        playlist.setPlaylistName(playlistName);
        playlist.setDiscordUserId(discordUserId);

        return playlist;
    }

    public static PlaylistClip makePlaylistClip(int playlistId, Clip clip, int displayOrder) {
        PlaylistClip playlistClip = new PlaylistClip();
        playlistClip.setPlaylistId(playlistId);
        playlistClip.setClip(clip);
        playlistClip.setDisplayOrder(displayOrder);

        return playlistClip;
    }

    public static ClipPlaylist makeClipPlaylist(int clipId, Playlist playlist, int displayOrder) {
        ClipPlaylist clipPlaylist = new ClipPlaylist();
        clipPlaylist.setClipId(clipId);
        clipPlaylist.setPlaylist(playlist);
        clipPlaylist.setDisplayOrder(displayOrder);

        return clipPlaylist;
    }

    public static DiscordUser makeWindTaUser() {
        return makeDiscordUser(221863292681977857L, "WindTa");
    }

    public static DiscordServer makeWindTaServer() {
        return makeDiscordServer(1161381438839607358L, "WindTa's server");
    }

    public static Clip makeWindTaClip() {
        return makeClip(1, "My Clip", "fSKQRDq3RkM",
                5, 10, 1, 1,
                221863292681977857L);
    }

    public static Playlist makeWindTasPlaylist() {
        return makePlaylist(1, "My Playlist", 221863292681977857L);
    }

    public static PlaylistClip makeWindTaPlaylistClip() {
        return makePlaylistClip(1, makeWindTaClip(), 1);
    }

    public static ClipPlaylist makeWindTaClipPlaylist() {
        return makeClipPlaylist(1, makeWindTasPlaylist(), 1);
    }
}

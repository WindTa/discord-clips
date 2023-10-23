package org.capstone;

import org.capstone.models.*;

import java.util.List;

public class DataHelper {

    public static DiscordUser makeWindTaUser() {
        return new DiscordUser(221863292681977857L, "WindTa");
    }

    public static DiscordServer makeWindTaServer() {
        return new DiscordServer(1161381438839607358L, "WindTa's server");
    }

    public static DiscordServer makeWindTaServerWithClips() {
        DiscordServer discordServer = makeWindTaServer();
        List<DiscordServerClip> discordServerClips = List.of(makeWindTaDiscordServerClip());
        discordServer.setClips(discordServerClips);
        return discordServer;
    }

    public static Clip makeWindTaClip() {
        return new Clip(1, "My Clip", "fSKQRDq3RkM",
                5, 10, 1, 1,
                221863292681977857L);
    }

    public static Clip makeWindTaClipWithPlaylists() {
        Clip clip = makeWindTaClip();
        List<ClipPlaylist> playlists = List.of(makeWindTaClipPlaylist());
        clip.setPlaylists(playlists);
        return clip;
    }

    public static Playlist makeWindTasPlaylist() {
        return new Playlist(1, "My Playlist", 221863292681977857L);
    }

    public static Playlist makeWindTasPlaylistWithClips() {
        Playlist playlist = makeWindTasPlaylist();
        List<PlaylistClip> clips = List.of(makeWindTaPlaylistClip());
        playlist.setClips(clips);
        return playlist;
    }

    public static PlaylistClip makeWindTaPlaylistClip() {
        return new PlaylistClip(1, makeWindTaClip(), 1);
    }

    public static ClipPlaylist makeWindTaClipPlaylist() {
        return new ClipPlaylist(1, makeWindTasPlaylist(), 1);
    }

    public static DiscordServerClip makeWindTaDiscordServerClip() {
        return new DiscordServerClip(1161381438839607358L, makeWindTaClip());
    }
}

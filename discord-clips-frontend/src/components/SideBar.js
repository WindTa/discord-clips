import { useState } from 'react';
import { Sidebar, Menu, MenuItem } from 'react-pro-sidebar';
import { Link } from 'react-router-dom';

import SubMenuCustom from './SubMenuCustom';

import { getPlaylistsByUser } from '../services/playlist'
import { getServersByUser } from '../services/server'

function SideBar({collapsed}) {
    const [activeMenuItem, setActiveMenuItem] = useState('Home');

    const isActive = (menuItem) => {
        return activeMenuItem === menuItem;
    };

    const setActive = (menuItem) => {
        console.log(menuItem);
        setActiveMenuItem(menuItem)
    }

    return (
        <Sidebar collapsed={collapsed} backgroundColor="#212529">
            <Menu
                menuItemStyles={{
                    root: {
                        fontSize: '16px',
                        fontWeight: 400,
                    },
                    button: ({ level, active, disabled }) => {
                        // only apply styles on first level elements of the tree
                        if (level === 0)
                            return {
                                backgroundColor: active ? '#5C64F4' : undefined,
                                '&:hover': {
                                    backgroundColor: '#5C64F4',
                                },
                        };
                        if (level === 1)
                            return {
                                backgroundColor: active ? '#5C64F4' : '#31373D',
                                '&:hover': {
                                    backgroundColor: '#5C64F4',
                                },
                        };
                    },
                }}
            >
                <MenuItem 
                    active={isActive('Home')}
                    onClick={() => setActive('Home')}
                    icon={<i className="bi bi-house"></i>}
                    component={<Link to="/" />}
                >
                    Home
                </MenuItem>

                <MenuItem 
                    active={isActive('Library')}
                    onClick={() => setActive('Library')}
                    icon={<i className="bi bi-collection-play"></i>}
                    component={<Link to="/library" />}
                >Library</MenuItem>
                <SubMenuCustom 
                    label='Playlists' 
                    path='playlist' 
                    getList={getPlaylistsByUser} 
                    isActive={isActive}
                    setActive={setActive}
                    />
                <SubMenuCustom 
                    label='Servers' 
                    path='server'
                    getList={getServersByUser} 
                    isActive={isActive}
                    setActive={setActive}
                    />
            </Menu>
        </Sidebar>
    );
}

export default SideBar;

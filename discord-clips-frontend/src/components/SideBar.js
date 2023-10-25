import { useState, useContext } from 'react';
import { Sidebar, Menu, MenuItem } from 'react-pro-sidebar';
import { Link } from 'react-router-dom';

import PlaylistSubMenu from './submenus/PlaylistsSubMenu';
import ServersSubMenu from './submenus/ServersSubMenu';

import AuthContext from '../contexts/AuthProvider';

function SideBar({collapsed}) {
    const { auth } = useContext(AuthContext);

    const [activeMenuItem, setActiveMenuItem] = useState('Home');

    const isActive = (menuItem) => {
        return activeMenuItem === menuItem;
    };

    const setActive = (menuItem) => {
        setActiveMenuItem(menuItem)
    }

    return (
        <Sidebar 
            collapsed={collapsed} 
            backgroundColor="#212529"
        >
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

                { Object.keys(auth).length !== 0
                    && (
                        <>
                            <MenuItem 
                                active={isActive('Library')}
                                onClick={() => setActive('Library')}
                                icon={<i className="bi bi-collection-play"></i>}
                                component={<Link to="/library" />}
                            >Library</MenuItem>
                            <PlaylistSubMenu
                                isActive={isActive}
                                setActive={setActive}
                                />
                            <ServersSubMenu
                                isActive={isActive}
                                setActive={setActive}
                                />
                        </>
                    )
                }
            </Menu>
        </Sidebar>
    );
}

export default SideBar;

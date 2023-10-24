import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import { MenuItem, SubMenu } from 'react-pro-sidebar';
function SubMenuCustom({label, path, getList, isActive, setActive}) {
	const [list, setList] = useState([]);
    const icon = label === "Playlists"
                    ? <i className="bi bi-music-note-list"></i>
                    : <i className="bi bi-discord"></i>;

    useEffect(() => {
        setList(getList);
    }, []);

    return (
        <SubMenu 
            label={label} 
            defaultOpen icon={icon}>
            {list.map((item, idx) => {
                return (
                    <MenuItem 
                        active={isActive(`${label}-${idx}`)}
                        onClick={() => setActive(`${label}-${idx}`)}
                        key={idx} icon={icon}
                        component={ <Link to={`/${path}`} /> }
                    >
                        {item}
                    </MenuItem>
                );
            })}
        </SubMenu>
    );
}
export default SubMenuCustom;



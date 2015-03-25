package com.ese.util.grid;

//Copyright (C) 2014 Codemerx

//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
//to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
//and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

//The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
//DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
//OR OTHER DEALINGS IN THE SOFTWARE.


    /**
    Represents a jqGrid row in the data send back as a response to jqGrid AJAX data request.
    */
    public class jqGridRow
    {
    	/**
        The unique ID of the jqGrid row represented by this instance of <code>JSONGridRow</code>
        */
        public String id;

        /**
        An array of objects representing the cell values of the jqGrid row represented by this instance of <code>JSONGridRow</code>.
        The number of elements in this array should equal the number of columns defined in jqGrid colModel.
        */
        public Object[] cell;

        /**
         Initializes a new instance of <code>JSONGridRow</code>.
         @param ID The unique ID of the jqGrid row represented by this instance of <code>JSONGridRow</code>
         @param cells An array of objects representing the cell values of the jqGrid row represented by this instance of <code>JSONGridRow</code>. The number of elements in this array should equal the number of columns defined in jqGrid colModel.           
         */
        public jqGridRow(String ID, Object[] cells)
        {
            id = ID;
            cell = cells;
        }
    }

